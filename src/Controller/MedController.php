<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

class MedController extends AbstractController
{
    /**
     * @Route("/med", name="med")
     * @IsGranted("ROLE_MEDECIN")
     */
    public function index(): Response
    {
        return $this->render('/frontMed.html.twig', [
            'controller_name' => 'MedController',
        ]);
    }
}
