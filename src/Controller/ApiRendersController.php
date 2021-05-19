<?php

namespace App\Controller;

use App\Entity\Planning;
use App\Entity\RendezVous;
use App\Repository\PlanningRepository;
use App\Repository\RendezVousRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

class ApiRendersController extends AbstractController
{
    /**
     * @Route("/apim/renders", name="apim_renders_index", methods={"GET"})
     */
    public function index(RendezVousRepository $Repository)
    {
        return $this->json($Repository->findAll(),200,[],['groups' => 'post:read2']);
    }

    /**
     * @Route("/apim/rend", name="apim_planning_add", methods={"POST"})
     */
    public function AddRend(Request $request, SerializerInterface $serializer,EntityManagerInterface $em)
    {
        $jsonRecu = $request->getContent();
        $rdv = $serializer->deserialize($jsonRecu,RendezVous::class,'json');
        //$planning->setCreatedAt(new \DateTime());
        $em->persist($rdv);
        $em->flush();
        dd($rdv);
    }

    /**
     * @Route("/apim/renders/removePlan/{id}", name="removePlan")
     */
    public function removeRenders($id)
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="Delete FROM rendez_vous WHERE id='$id' ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        // dd($serializer);
        return new JsonResponse("deleted");

    }
    /**
     * @Route("/apim/renders/updateRDV/{id}/{nom_rdv}/{description}/{date_rdv}", name="updateRenders", methods={"PUT"})
     */
    public function updateRenders(Request $request,$id,$nom_rdv,$description,$date_rdv) :Response
    {
        $conn = $this->getDoctrine()->getManager()->getConnection();
        $RAW_QUERY="UPDATE rendez_vous SET description='$description', nom_rdv='$nom_rdv', date_rdv= '$date_rdv'  WHERE id='$id' ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("updated");

    }

    /**
     * @Route("/apim/renders/addRDV/{patient_id}/{user_id}/{nom_rdv}/{description}/{date_rdv}", name="AddRenders" ,methods={"POST"})
     * @throws \Exception
     */
    public function AddRenders(Request $request,$patient_id,$user_id,$nom_rdv,$description,$date_rdv) :Response
    {
        $entityManager=$this->getDoctrine()->getManager()->getConnection();
        //$date = new \DateTime($date_rdv);
        $RAW_QUERY="insert into rendez_vous(patient_id,user_id,nom_rdv,description,date_rdv) values ($patient_id,$user_id,$nom_rdv,$description,$date_rdv) ";
        $statement= $entityManager-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new \Symfony\Component\Serializer\Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("Bien Ajouter");

    }
}
